for /D /R %%i in (*.*) do ( 

cd %%i 

type *.jsp>>D:\source.txt
type *.java>>D:\source.txt
type *.xml>>D:\source.txt
type *.html>>D:\source.txt
type *.js>>D:\source.txt

cd .. 
) 