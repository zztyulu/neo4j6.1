import pymysql
import csv
import codecs
import sys
def main(argv):
	db= pymysql.connect(host="localhost",user=argv[0],
	     password=argv[1],db=argv[2],port=3306)
	cur = db.cursor()
	path=argv[3]
	print(argv)
	#print(path)
	sql0='show tables'
	cur.execute(sql0)
	table_list=[]
	table_results = cur.fetchall()
	#print(len(results))
	for i in range(len(table_results)):
		table_list.append((table_results[i][0]))
	for table_name in table_list:
		sql = 'select * from %s into outfile "'+path+ '%s.csv" fields terminated by \',\' ' 
		#print(sql%(table_name,table_name))
		cur.execute(sql%(table_name,table_name))
	#table_list=['aclineend','aclinesegment','substation']
	#1.查询操作
	# 编写sql 查询语句  user 对应我的表名
	#sql = "select * from INFORMATION_SCHEMA.KEY_COLUMN_USAGE a WHERE a.TABLE_NAME='aclineend'"
	for table_name in table_list:
		sql = "select * from INFORMATION_SCHEMA.KEY_COLUMN_USAGE a WHERE a.TABLE_NAME='"+table_name+"'"
		cur.execute(sql)
		results = cur.fetchall()
		primarykey=results[0][6] #m_id
		for row in results[1:]:
			column_name = row[6]
			fori_table = row[10]
			fori_name = row[11]
			csv_name = primarykey+'_'+column_name+'_'+fori_table+'.csv'
			#sql2 ='select '+primarykey+','+column_name+' from '+table_name+' into outfile "F:/dw/csv/'+csv_name+'"'
			#sql3 = 'select %s,%s from %s into outfile "F:/dw/csv/524/%s" fields terminated by \',\''
			sql3 = 'select %s,%s from %s into outfile "'+path+ '%s" fields terminated by \',\''
			#print(sql3%(primarykey,column_name,table_name,table_name))
			cur.execute(sql3%(primarykey,column_name,table_name,csv_name))

	db.close()    #关闭连接

if __name__ == '__main__':
    main( sys.argv[1:] )