@echo off
echo Batch started on %date% %time% - %* >> "%~dp0log\psexec.log"
net use z: \\london\cdic_share replace_with_your_password /user:replace_with_your_username
"%~dp0jre6\bin\java.exe" -cp "%~dp0conf\;%~dp0lib\*" -DLOG_HOME="%~dp0log" -DFOLDER.PROCESS="Z:/process" -DFOLDER.PROCESS.OUT="Z:/process/out" org.springframework.batch.core.launch.support.CommandLineJobRunner batch.xml %*
net use z: /delete
echo Batch ended on %date% %time% - %* >> "%~dp0log\psexec.log"
