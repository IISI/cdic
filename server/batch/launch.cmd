rem net use z: \\london\cdic_share replace_with_your_password /user:replace_with_your_username
subst z: E:\@IISI\@Project\Citibank\7939\work\temp
rem "%~dp0jre6\bin\java.exe" -jar "%~dp0batch-1.0-SNAPSHOT.jar" batch.xml convertJob
java -jar ./target/batch-1.0-SNAPSHOT.jar batch.xml convertJob
rem net use z: /delete
