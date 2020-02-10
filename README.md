Users
   -id
   -nume
   -parola_hash
   -statistici( nr de joburi postate, bani primiti, bani dati, joburi create, joburi asignate)
   -trust(0 -> 10)
   
   
Job
  -id
  -id_user (Users)
  -category (Category)
  -titlu
  -descriere
  -data_postare
  -estimare_duratie
  -status
  -price
  -position_X
  -position_y
  
  
Category
  -id
  -name
  
Requirements
  -id
  -categorie
  -nume
  -typeField
  
  
  
Ratings:
	-id
	-titlu
	-deLaCine (Users)
	-pentruCine (Users)
	-data
	-nr_stele
	
	
----------------------------------------

/login
/register
/logout
/user/{id}/addJob
/user/{id}/requestJob
/user/{id}/filterJobs/{filters}
/user/{id}/jobs/{}
/user/{id}/review/{target_job}


branch tudor
   Branch Patrick
