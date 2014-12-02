cd C:\Users\Florian\Documents\GitHub\J2EE\J2EE\testb\TestBase
set class=com.testBase.fonctionWebService.TestBaseDBManager
set clpth=./war/WEB-INF/classes
set resourcedir=./war
set outsourcedir=./src
set outdir=./war/WEB-INF/classes
set JDK_HOME=C:\Program Files\Java\jdk1.8.0_20
"%JDK_HOME%\bin\wsgen" -cp "%clpth%" -wsdl -keep -r "%resourcedir%" -d "%outdir%" -s "%outsourcedir%" %class%
pause