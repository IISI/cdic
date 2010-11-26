DELETE FROM HOSTFILESTS;
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF02I',232);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF02R',232);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF03' ,358);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF04' ,130);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF07' , 77);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF10' ,151);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF12' ,363);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF14' , 99);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF15' ,164);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF21I',123);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF21R',123);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF22I', 53);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF22A', 53);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF23' ,158);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF24' ,122);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF25' ,137);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICT03G', 51);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICT27' , 96);
INSERT INTO HOSTFILESTS (NAME,RECLEN) VALUES ('CDICF22R',400);

DELETE FROM LOCALFILESTS;
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICF11', 9);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICF20', 5);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT04', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT06', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT08', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT09', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT10', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT11', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT13', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT14', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT18', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT20', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('CDICT21', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('JointAcclist', 2);
INSERT INTO LOCALFILESTS (NAME, COLUMNCOUNT) VALUES ('FMBCDWN4', 3);

DELETE FROM CDICFILESTS;
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F01', 'A11');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES ('1',  'F02', 'A21 B21 C21');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES ('1',  'F03', 'A22 B22 C22');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES ('1',  'F04', 'A23');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F05', 'A24');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F07', 'A26 B26 C26');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES ('1',  'F08', 'A31 B31');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F10', 'A34');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F11', 'B35');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F12', 'A41');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F14', 'A43');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F15', 'A44');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F18', 'A61');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F21', 'A73 B73 C73');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F22', 'A74');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F23', 'A75');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F24', 'A76');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F25', 'A77');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T02', 'T02');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES ('2',  'T03', 'T03');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T04', 'T04');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T06', 'T06');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T08', 'T08');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T09', 'T09');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T10', 'T10');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T11', 'T11');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES ('2',  'T12', 'T12');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T13', 'T13');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T14', 'T14');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T18', 'T18');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T20', 'T20');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T21', 'T21');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'T27', 'T27');
INSERT INTO CDICFILESTS (FILEGROUP,FILENO,SUBFILE) VALUES (NULL, 'F99', NULL);

DELETE FROM FILEDEPEND;
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F02', 'CDICF02I', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F02', 'CDICF02R', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F03', 'CDICF03', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F04', 'CDICF04', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F07', 'CDICF07', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F07', 'F02', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F07', 'F03', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F07', 'F04', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F08', 'JointAcclist', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F10', 'CDICF10', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F11', 'CDICF11', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F12', 'CDICF12', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F14', 'CDICF14', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F15', 'CDICF15', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F18', 'F02', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F18', 'F03', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F18', 'F04', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F18', 'F05', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F18', 'CDICF20', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F21', 'CDICF21I', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F21', 'CDICF21R', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F22', 'CDICF22I', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F22', 'CDICF22A', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F22', 'CDICF22R', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F23', 'CDICF23', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F24', 'CDICF24', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F25', 'CDICF25', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T02', 'FMBCDWN4', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T03', 'CDICT03G', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T04', 'CDICT04', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T06', 'CDICT06', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T06', 'CDICT03G', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T08', 'CDICT08', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T09', 'CDICT09', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T10', 'CDICT10', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T11', 'CDICT11', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T12', 'CDICT03G', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T13', 'CDICT13', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T14', 'CDICT14', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T18', 'CDICT18', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T20', 'CDICT20', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T21', 'CDICT21', 'LOCAL');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('T27', 'CDICT27', 'HOST');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F02', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F03', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F04', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F05', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F07', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F10', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F12', 'CDIC');
INSERT INTO FILEDEPEND (FILENO,DEPENDENCY,DEPTYPE) VALUES ('F99', 'F24', 'CDIC');
