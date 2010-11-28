Module wrapper
    Private Declare Ansi Function GetPrivateProfileString _
      Lib "kernel32.dll" Alias "GetPrivateProfileStringA" _
            (ByVal lpApplicationName As String, _
            ByVal lpKeyName As String, ByVal lpDefault As String, _
            ByVal lpReturnedString As String, _
            ByVal nSize As Integer, ByVal lpFileName As String) _
            As Integer
    Public gstrBACKFLAG As String   'S:表示只有一個F/E的權限,不用回Login,M:表示有多個要回Login
    Public gstrUsrID As String
    Public gstrUsrName As String
    Public gstrSysID As String
    Public gstrGrpID As String
    Public gstrUsrPwd As String
    Public gstrIP As String
    Public gstrDbServerName As String

    '定義Command Line各欄位的長度
    Public Const intFLAGLEN = 1
    Public Const intUSRIDLEN = 10
    Public Const intSYSIDLEN = 4
    Public Const intUSRPWDLEN = 16
    Public Const intGRPIDLEN = 10
    Public Const intUSRNAME = 30
    Public Const intIP = 15
    Public Const intDBSERVERNAME = 30


    Sub Main()
        Dim strComLine As String
        Dim id As Integer

        strComLine = Command()

        'flag
        gstrBACKFLAG = Mid(strComLine, 1, intFLAGLEN)
        strComLine = Mid(strComLine, intFLAGLEN + 1)
        'Console.WriteLine(gstrBACKFLAG)

        'userid
        gstrUsrID = Trim(Mid(strComLine, 1, intUSRIDLEN))
        strComLine = Mid(strComLine, intUSRIDLEN + 1)
        'Console.WriteLine(gstrUsrID)

        'usrpwd
        gstrUsrPwd = AsciiStringToHexString(Trim(Mid(strComLine, 1, intUSRPWDLEN)))
        strComLine = Mid(strComLine, intUSRPWDLEN + 1)
        'Console.WriteLine(gstrUsrPwd)

        'sysid
        gstrSysID = Mid(strComLine, 1, intSYSIDLEN)
        strComLine = Mid(strComLine, intSYSIDLEN + 1)
        'Console.WriteLine(gstrSysID)

        'grpid
        gstrGrpID = Trim(Mid(strComLine, 1, intGRPIDLEN))
        strComLine = Mid(strComLine, intGRPIDLEN + 1)
        'Console.WriteLine(gstrGrpID)

        gstrUsrName = Trim(MidB(strComLine, 1, intUSRNAME))
        strComLine = Mid(strComLine, intUSRNAME + 1 - (intUSRNAME - Len(MidB(strComLine, 1, intUSRNAME))))
        'Console.WriteLine(gstrUsrName)

        gstrIP = Trim(Mid(strComLine, 1, intIP))
        strComLine = Mid(strComLine, intIP + 1)
        'Console.WriteLine(gstrIP)

        gstrDbServerName = Trim(strComLine)
        'Console.WriteLine(gstrDbServerName)

        'Console.WriteLine(GetIniSetting("prog"))

        id = Shell(GetIniSetting("prog") + " ""flag=" + gstrBACKFLAG + """ ""userId=" + gstrUsrID + """ ""userPassword=" + gstrUsrPwd + """ ""systemId=" + gstrSysID + """ ""groupId=" + gstrGrpID + """ ""userName=" + gstrUsrName + """ ""ip=" + gstrIP + """ ""dbServerName=" + gstrDbServerName + """", AppWinStyle.Hide, False, 1)
        'Console.WriteLine(id)
        Environment.Exit(0)

    End Sub

    Public Function GetIniSetting(ByVal strKey As String, Optional ByVal strSection As String = "settings") As String
        Dim strValue As String
        Dim intPos As Integer
        On Error GoTo ErrTrap
        strValue = Space(1024)
        GetPrivateProfileString(strSection, strKey, "NOT_FOUND", strValue, 1024, ".\config.ini")
        Do While InStrRev(strValue, " ") = Len(strValue)
            strValue = Mid(strValue, 1, Len(strValue) - 1)
        Loop
        ' to remove a special chr in the last place
        strValue = Mid(strValue, 1, Len(strValue) - 1)
        GetIniSetting = strValue
ErrTrap:
        If Err.Number <> 0 Then
            Err.Raise(Err.Number, , "Error form Fucntions.GetIniSettings " & Err.Description)
        End If
    End Function

    Function AsciiStringToHexString(ByVal asciiString As String) As String
        Dim ascii() As Byte = System.Text.Encoding.Default.GetBytes(asciiString)
        Dim count As Integer = ascii.Length
        Dim hexArray(count - 1) As String
        For idx As Integer = 0 To count - 1
            hexArray(idx) = ascii(idx).ToString("x2")
        Next
        Return String.Join("", hexArray)
    End Function

    Public Function MidB(ByVal Expression As String, _
     ByVal Start As Integer, _
     ByVal Length As Integer) As String
        Dim i As Integer, j As Integer = 0
        Dim strBig5() As Byte = System.Text.Encoding.GetEncoding(950).GetBytes(Expression)

        If Length >= strBig5.Length Then
            If Start = 1 Then Length = strBig5.Length
            If Start <> 1 Then Length = strBig5.Length - (Start - 1)
            If Length <= 0 Then Return ""
        End If

        Dim strTmp(Length - 1) As Byte
        For i = Start - 1 To Start + Length - 2
            strTmp(j) = strBig5(i)
            j += 1
        Next

        Return System.Text.Encoding.GetEncoding(950).GetString(strTmp)
    End Function
End Module
