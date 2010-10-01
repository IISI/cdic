net use z: \\london\cdic_share replace_with_your_password /user:replace_with_your_username
"%~dp0jre6\bin\java.exe" -jar "%~dp0batch-1.0-SNAPSHOT.jar" batch.xml convertJob
net use z: /delete
