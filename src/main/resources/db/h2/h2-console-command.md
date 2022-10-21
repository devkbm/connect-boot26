# H2 CONSOLE CSV EXPORT COMMAND
call CSVWRITE ( 'C:/temp/MyCSV.txt', 'SELECT * FROM [table_name]','charset=UTF-8 fieldSeparator=, fieldDelimiter=\"')

# 테이블 생성 및 데이터 생성 스크립트 
script table [table_name]