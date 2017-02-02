cls
rmdir target /s/q
rmdir D:\concordion-kickstart-1.5.0\example\test\report\com /s/q
rmdir D:\concordion-kickstart-1.5.0\example\test\report\example /s/q
rmdir D:\concordion-kickstart-1.5.0\example\test\report\image /s/q
del D:\concordion-kickstart-1.5.0\example\test\report\*.* /Q
cls
mvn clean compile package install test -DpageurlsFileName=src/test/resources/pageurls/qa.pageurls.properties -DconfigFileName=src/test/resources/config.properties
@rem mvn clean compile -DpageurlsFileName=src/test/resources/pageurls/qa.pageurls.properties -DconfigFileName=src/test/resources/config.properties
