# Researching Commands - `find` Command

## -type
Type option specifies whether the path should search for file, directory, symlink or device files.
The type is specified as, f or d or l or c or s, which each signifies file/directory/link/device/socket respectively.

```
chris@Chriss-MacBook-Air technical % find * -type d
911report
biomed
government
government/About_LSC
government/Env_Prot_Agen
government/Alcohol_Problems
government/Gen_Account_Office
government/Post_Rate_Comm
government/Media
plos
```
The find shows directories only when searched on `./technical` as an asterisk. This is useful when one is trying to see what types of directories are listed under the path while removing all the noisy outputs with paths with many files such as the `/technical`.

```
chris@Chriss-MacBook-Air technical % find ?????? -type d
biomed
```
On the otherhand if I wanted to search for a specific directory that is n-length long, I could just use the commandline patterns (six question marks in the example) to specify how length I'm looking for and let `find` output all directories of that length.

```
chris@Chriss-MacBook-Air technical % find * -type f
911report/chapter-13.4.txt
911report/chapter-13.5.txt
911report/chapter-13.1.txt
911report/chapter-13.2.txt
911report/chapter-13.3.txt
911report/chapter-3.txt
911report/chapter-2.txt
911report/chapter-1.txt
911report/chapter-5.txt
911report/chapter-6.txt
...
```
The example shows all files (while excluding the directories) when searched on `./technical` as an asterisk. It is especially useful when one would want to list only the files to be outputed to run additional commands on those files.

## -size \<N\>
The size option specifies path size of exactly N, or at least N if appended by plus sign, at most N if appended by minus sign. The default unit is in 512 byte blocks, but unit can be changed with added suffixes as k for kilobytes, M for megabytes and G for gigabytes.

```
chris@Chriss-MacBook-Air technical % find * -size 16033c        
plos/pmed.0020232.txt
```
The example shows file resulting exactly 16033 bytes. Although such specific size might have fewer real world uses, it can be useful when the size is known, or a specific/uniform size must be kept for certain files.

```
chris@Chriss-MacBook-Air technical % find * -size -500c 
government
government/Alcohol_Problems
```
The example shows all files under `/technical` that is under 500 bytes. We can see that only one file fits the criteria. As such, the upperbound can be useful to quickly search files that have lesser content when there are many files.

```
chris@Chriss-MacBook-Air technical % find * -size +150k
911report/chapter-13.4.txt
911report/chapter-13.5.txt
911report/chapter-3.txt
government/About_LSC/commission_report.txt
government/Env_Prot_Agen/multi102902.txt
government/Env_Prot_Agen/bill.txt
government/Env_Prot_Agen/tech_adden.txt
government/Gen_Account_Office/GovernmentAuditingStandards_yb2002ed.txt
government/Gen_Account_Office/Statements_Feb28-1997_volume.txt
government/Gen_Account_Office/pe1019.txt
government/Gen_Account_Office/d01591sp.txt
```
The example shows all files under `/technical` that is over 150 kibibytes. We can see several (out of many) fits the criteria. Such lowerbound can be useful to make sure if a certain file is larger than other files, which can be especially useful when the storage is limited and file size has limitations.

## -exec \<cmd\>
The execute option executes the command on the list of files returned by find. The end of command must be indicated by an escaped semicolon (\;). In addition, the open/closed braclets `{}` within the command indicates where the found path should be appended.

```
chris@Chriss-MacBook-Air technical % find * -size +150k -exec wc {} \;  
    2941   34343  265912 911report/chapter-13.4.txt
    3237   37985  290993 911report/chapter-13.5.txt
    3159   33834  264360 911report/chapter-3.txt
    3798   34774  223414 government/About_LSC/commission_report.txt
    3530   27820  195851 government/Env_Prot_Agen/multi102902.txt
    7065   38177  243860 government/Env_Prot_Agen/bill.txt
    3243   26956  178501 government/Env_Prot_Agen/tech_adden.txt
    6001   35744  246218 government/Gen_Account_Office/GovernmentAuditingStandards_yb2002ed.txt
    6114   46362  306011 government/Gen_Account_Office/Statements_Feb28-1997_volume.txt
    4228   30526  202066 government/Gen_Account_Office/pe1019.txt
    5695   45810  302040 government/Gen_Account_Office/d01591sp.txt
```
The example shows the files over 150kB from previous example and runs word count on the found files.
This can be very useful when one is trying to specify specific files to observe the contents of the file.

```
chris@Chriss-MacBook-Air technical % find * -size +150k -exec rm -i {} \;
remove 911report/chapter-13.4.txt? n
remove 911report/chapter-13.5.txt? n
remove 911report/chapter-3.txt? n
remove government/About_LSC/commission_report.txt? n
remove government/Env_Prot_Agen/multi102902.txt? n
remove government/Env_Prot_Agen/bill.txt? n
remove government/Env_Prot_Agen/tech_adden.txt? n
remove government/Gen_Account_Office/GovernmentAuditingStandards_yb2002ed.txt? n
remove government/Gen_Account_Office/Statements_Feb28-1997_volume.txt? n
remove government/Gen_Account_Office/pe1019.txt? n
remove government/Gen_Account_Office/d01591sp.txt? n
```
Directly following from the previous example, now the exec is run with remove command with -i option, which make sure the system prompts a confirmation efore each removal. Because we are searching for a file larger than certain size, it is a very applicable situation where large files have to be removed.

```
chris@Chriss-MacBook-Air technical % find 911report -type f -name "*.txt" -exec grep 'conspiracy' {} \;
                Princess Haifa al Faisal provided any funds to the conspiracy, either directly or
                together with Yousef for the Manila air conspiracy.
                operational leader of the 9/11 conspiracy, Mohamed Atta, went online from Hamburg,
                him with conspiracy to attack U.S. defense installations. The indictment also
                Ziad Jarrah would all become key players in the 9/11 conspiracy.
                be a "Jewish world conspiracy." He proclaimed that the highest duty of every Muslim
            After the Hamburg recruits joined the 9/11 conspiracy, al Qaeda began giving them
            KSM says that though he told others involved in the conspiracy to stay away from
                    could aid attacks. Two al-Qua' da members found guilty in the conspiracy to bomb
                nebulous conspiracy with no territories or citizens or assets that could be readily
```
This time, the example is restricted to 911reports and searched the text files that have the word 'conspiracy' inside the files. As expected, nothing interesting is found from the government reports. This feature is useful when we would like to search for specific keywords within files that matches the find results.

---
**References**: https://man7.org/linux/man-pages/man1/find.1.html
