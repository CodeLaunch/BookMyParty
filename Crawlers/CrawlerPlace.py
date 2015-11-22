from googleplaces import GooglePlaces, types, lang
import MySQLdb

def addtable2(Name,Address,website,Phone,Mobile,lattitude,longitude):
    con = MySQLdb.connect(host='127.0.0.1', user='root', passwd='r00t', db='cater_db')
    cur = con.cursor()
    
    cur.execute('INSERT INTO caters2(Name,Address,website,Phone,Mobile,lattitude,longitude) values (%s,%s,%s,%s,%s,%s,%s)',(Name,Address,website,Phone,Mobile,lattitude,longitude)) 
    con.commit()
    mid = cur.lastrowid
    con.close()
    return mid

API_KEY = 'AIzaSyCtGoyJVBHQ_mG8NaXST6jiTvltUtmbCVU'
google_places = GooglePlaces(API_KEY)

# You may prefer to use the text_search API, instead.

location=raw_input('Enter Location: ')

Search=raw_input('Enter Search: ')

query_result = google_places.nearby_search(
        location=location, keyword=Search,
        radius=30)

if query_result.has_attributions:
    print query_result.html_attributions
index=1
Results = []
for place in query_result.places:
    Result = []
    # Returned places from a query are place summaries.
    Name = ''
    lattitude = ''
    longitude = ''
    website = ''
    address = ''
    phone = ''
    mobile = ''
    print 'Index:',index
    #Result.append(index)
    index=index+1
    print 'Name:',place.name
    Name = place.name
    #Result.append(place.name)
    print 'Geographic Location:',place.geo_location['lat'],place.geo_location['lng']
    lattitude = place.geo_location['lat']
    longitude = place.geo_location['lng']
    #Result.append(place.geo_location['lat'])
    #Result.append(place.geo_location['lng'])
    #print place.reference

    # The following method has to make a further API call.
    place.get_details()

    # A dict matching the JSON response from Google.
    try:
        print 'WebSite:',place.details['website']
        website = place.details['website']
        #Result.append(place.details['website'])
    except Exception,e:
        #Result.append(" ")
        print `e`
    try:
        print 'Address:',place.details['vicinity']
        address = place.details['vicinity'].replace(",", " ")
        #Result.append(address)
    except Exception,e:
        #Result.append(" ")
        print `e`        
    print 'Type:',place.details['types'][0]
    #Result.append(place.details['types'][0])
    print place.local_phone_number
    phone = place.local_phone_number
    #Result.append(place.local_phone_number)
    print place.international_phone_number
    mobile = place.international_phone_number
    addtable2(Name,address,website,phone,mobile,lattitude,longitude)
