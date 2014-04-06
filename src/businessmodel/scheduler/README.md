time slot sequentie nummer geven zodat een order kan verplicht worden 
om in opeen volgende timeslots ingepland te worden of een andere
implementatie eerder sequentie geven aan een soort van workslot ?
	=> een normaal order moet opschuiven in het timeslot
	
als in een freeshift een timelot verdwijnt moet een order pas 
terug aan de te schedulen orders worden toegevoegd wanneer hij
uit de eerste positie van een timeslot wordt verwijderd.

in een endshift moet een order verwijderd worden van zodra hij 
in een van de workpost slots van de 