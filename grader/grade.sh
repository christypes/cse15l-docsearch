# Create your grading script here
# set -e

rm -rf student-submission           # Clears previous submission
git clone $1 student-submission     # Clones new submission to local

# https://github.com/ucsd-cse15l-f22/list-methods-lab3
# https://github.com/ucsd-cse15l-f22/list-methods-corrected     ... fixed
# https://github.com/ucsd-cse15l-f22/list-methods-compile-error ... syntax error
# https://github.com/ucsd-cse15l-f22/list-methods-signature     ... wrong order for filter
# https://github.com/ucsd-cse15l-f22/list-methods-filename      ... wrong filename
# https://github.com/ucsd-cse15l-f22/list-methods-nested        ... nested directory
# https://github.com/ucsd-cse15l-f22/list-examples-subtle       ... subtle mistakes

echo 'Grading..'
echo '----------------------------------------------------------'
SCORE=0;
JPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'
# javac -cp $JUPATH TestListExamples.java
if [[ -e student-submission/ListExamples.java ]]; then
    echo '[+1 point] Submitted with correct file name and path!!'
    SCORE=$(( SCORE + 1 ))
    cp student-submission/ListExamples.java .
    echo > CompileErrors.log # Clears log from previous
    echo > TestErrors.log    # Clears log from previous
    javac -cp $JPATH ListExamples.java TestListExamples.java 2> CompileErrors.log
    if [[ $? -eq 0 ]]; then
        SCORE=$(( SCORE + 1 ))
        echo '[+1 point] File compiled with success!!'
        java -cp $JPATH org.junit.runner.JUnitCore TestListExamples 1> TestErrors.log
        if grep -q 'testFilter' TestErrors.log; then
            echo '[+0 point] Filter - Test failed.. check TestErrors.log for details'
        else
            SCORE=$(( SCORE + 1 ))
            echo '[+1 point] Filter - Regular case success!!'
        fi
        if grep -q 'testMergeReg' TestErrors.log; then
            echo '[+0 point] Merge - Regular case failed.. check TestErrors.log for details'
        else
            SCORE=$(( SCORE + 1 ))
            echo '[+1 point] Merge - Regular case success!!'
        fi
        if grep -q testMergeDupe TestErrors.log; then
            echo '[+0 point] Merge - Duplicate case failed.. check TestErrors.log for details'
        else
            SCORE=$(( SCORE + 1 ))
            echo '[+1 point] Merge -  Duplicate case success!!'
        fi
    else
        echo '[+0 point] Compile errors found. Check CompileErors.log'
    fi
else
    echo '[+0 point] File not found, expected path is: student-submission/ListExamples.java'
fi
echo '----------------------------------------------------------'
echo "Total score is ${SCORE}/5 points"

# if [[ -e ListExamples.java ]]; then rm ListExamples.java; fi    # Removes copied java file