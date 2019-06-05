import sys;
xml_path=sys.argv[1]
#xml_path=input('请输入xml文件路径\n')
#KV_file_path=input('请输入中间结果文件路径\n')
#KV_file_path='log.txt'
KV_file_path=sys.argv[2]
def readXml(path_name,KV_file_path):
	files=open(path_name,'rb')
	files=files.readlines()

	f=open(KV_file_path,'w')
	for line in files:
		line=line.strip()
		l=line.split()
		#f.write(str(l[0])+'\n')
		if str(l[0])=='b\'<UML:Class\'' or str(l[0])=='b\'<UML:Operation\'' or str(l[0])=='b\'<UML:Attribute\'':
			l1=str(line).split('=')
			l2=(str(l1[0])[7:]+' '+str(l1[1])).split()
			if str(l2[2])=="\"EARootClass\"":
				continue
			s=''
			if l2[2][0]=='\"':
				s=l2[2][1:]
			if l2[2][-1]=='\"':
				s=s[:-1]
			f.write(str(l2[0])+' '+s+'\n')
			#f.write(str(line)+'\n')
		if str(l[0])=='b\'<UML:Association\'':
			l1=str(line).split('=',1)
			t1=str(l1[0])[7:].split()
			if t1[1]!='name':
				f.write(t1[0]+' '+'FK'+' ')
				continue
			l2=str(l1[1]).split()
			f.write(t1[0]+' '+str(l2[0])[1:]+str(l2[1])+str(l2[2])[:-1]+' ')
		if str(l[0])=='b\'<UML:TaggedValue\'' and str(l[1])=='b\'tag=\"ea_sourceName\"\'':
			l1=str(l[2])
			if l1[-2]!='>':
				f.write(l1[9:-1]+' ')
				continue
			f.write(l1[9:-4]+' ')
		if str(l[0])=='b\'<UML:TaggedValue\'' and str(l[1])=='b\'tag=\"ea_targetName\"\'':
			l1=str(l[2])
			if l1[-2]!='>':
				f.write(l1[9:-1]+' ')
				continue
			f.write(l1[9:-4]+'\n')
	f.close()

def getTuples(KV_file_path):
	f=open(KV_file_path)
	lines=f.readlines()
	class_name=[]
	info={}
	Association=[]
	tuples=[]
	for line in lines:
		line=line.split()
		if line[0]=='Class':
			class_name.append(line[1])
			info[line[1]]={'Attribute':[],'Operation':[]}
		elif line[0]=='Association':
			Association.append(line[1:])
		else:
			info[class_name[-1]][line[0]].append(line[1])
	for ass in Association:
		if ass[1] in class_name and ass[2] in class_name:
			tuples.append(str((ass[1],ass[0],ass[2])))
			#tuples.append((ass[1],ass[0],ass[2]))
	return info,tuples,class_name
if __name__=='__main__':
	readXml(xml_path,KV_file_path)
	info,tuples,class_name=getTuples(KV_file_path)
	result=sys.argv[3]
	q=open(result,'w')
	q.write('\n'.join(tuples))
	print('sucess')