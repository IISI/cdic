@echo off
net use z: \\london\cdic_share replace_with_your_password /user:replace_with_your_username
"%~dp0jre6\bin\java.exe" -cp "%~dp0conf\;%~dp0lib\*" org.springframework.batch.core.launch.support.CommandLineJobRunner batch.xml %1
net use z: /delete
