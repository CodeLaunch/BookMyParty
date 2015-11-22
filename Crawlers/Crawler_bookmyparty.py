#URL http://bookmyparty.co.in/SearchList.aspx?q=Caterers&Page=1 25


import requests
import BeautifulSoup
import re
import MySQLdb


def addtable1(Name,POC,Address,Phone,Mobile,Cuisines,map_address):
    con = MySQLdb.connect(host='127.0.0.1', user='root', passwd='r00t', db='cater_db')
    cur = con.cursor()
    
    cur.execute('INSERT INTO caters(Name,POC,Address,Phone,Mobile,Cuisines,map_address) values (%s,%s,%s,%s,%s,%s,%s)',(Name,POC,Address,Phone,Mobile,Cuisines,map_address)) 
    con.commit()
    mid = cur.lastrowid
    con.close()
    return mid

def get_data(link):
	try:
		i = 1
		while i<26:
			link=link+str(i)
			req=requests.get(link,headers=headers)
			data=req.text
			soup=BeautifulSoup.BeautifulSoup(''.join(data))
			#category=[]
			Cater_Name=''
			Cater_POC = ''
			Cater_address = ''
			Cater_cuisines = ''
			Cater_PhoneNo = ''
			Cater_MobileNo = ''
			Cater_map = ''
			for div in soup.findAll('div',{'class':'minMicro'}):
				h1 =  div.find('h1',{'class':'prpl'})
				Cater_Name =  h1.text
				p1 = div.find('p',{'style':'display:none;'})
				Cater_POC = p1.text
				try:
					p2 =  div.find('p',{'class':'phn'})
					Cater_PhoneNo =  p2.text
				except Exception,e:
					print str(e)
				try:
					p3 =  div.find('p',{'class':'mob'})
					Cater_MobileNo =  p3.text
				except Exception,e:
					print str(e)
				for bold in div.findAll('b'):
					if bold.parent.name == 'p':
						try:
							if bold.text == 'Cuisines':
								Cater_cuisines = bold.parent.text
						except Exception,e:
							print str(e)
						try:
							if bold.text == 'Address':
								Cater_address = bold.parent.text
								Cater_address = Cater_address.replace("Address: ","")
								Cater_address = Cater_address.replace("|View Map","")
								Cater_address = ' '.join(Cater_address.split())
						except Exception,e:
							print str(e)
				Cater_map = div.find('a',{'class':'mp'})
				Cater_map = Cater_map['href'] 
				addtable1(Cater_Name,Cater_POC,Cater_address,Cater_PhoneNo,Cater_MobileNo,Cater_cuisines,Cater_map)	

	except Exception,e:
			print str(e)



headers  ={'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0'}
link= 'http://bookmyparty.co.in/SearchList.aspx?q=Caterers&Page='

get_data(link)