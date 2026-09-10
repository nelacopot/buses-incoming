## Navodila za zagon
`
git clone https://github.com/nelacopot/buses-incoming.git
`

`
cd buses-incoming
`

`
java -jar buses-incoming.jar busTrips <station_id> <num_buses_per_line> <relative|absolute>
`

## Moj pristop k aplikaciji

v aplikaciji sem uporabljala naslednje GTFS dokumente:
- stops.txt
- stop_times.txt
- trips.txt
- routes.txt

__calendar.txt__ se mi je zdelo nesmiselno uporabljati, saj je njegova vsebina taka, da nakazuje na to, da vsi avtobusi vozijo vsak dan v tednu. 
Obravnava te datoteke tako ne bi spremenila ničesar.

---

Prav tako nisem uporabljala datotek v celoti, saj kljub večim poljem (ločenim z vejico), za namen te naloge nisem nikoli potrebovala več kot prvih 5 polij
Zaradi tega sem testne datoteke spisala v okrnjeni obliki
Na primer: 
- stop_times.txt ima polja: trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled,timepoint
- test_stop_times.txt (za namene unit testiranja) ima le polja: trip_id,arrival_time,departure_time,stop_id

V razredu, ki pa skrbi za kategorizacijo datoteke stop_times.txt pa sem zato split po vejicah omejila na 5 delov (tako razdeli po vejicah na pet delov - 5. del so vsa polja, ki jih drugače ne potrebujemo)

---

Eno od glavnih optimizacij sem naredila pri obdelavi stop_times.txt. Ker gre za zelo veliko datoteko, jo obdelujem streamovsko, vrstico po vrstici, 
namesto da bi jo celotno naložila v pomnilnik. Poleg tega čas parse-am samo za tiste zapise, ki pripadajo postaji, ki jo uporabnik išče.

---

Še ena optimizacija je pri trips.txt in routes.txt, ki ju spremenim v Map, kar pohitri moje iskanje po povezavah med različnimi ID-ji

---


Okolje, v katerem sem napisala program: __IntelliJ IDEA__

## Raba umetne inteligence

Umetno inteligenco sem uporabila za priporočilo začetni o razdelitvi datotečnega sistema in za rešitev problema, ki ga nisem znala rešiti, in sicer: kako 
napisati Unit Test za izpis.
Občasno mi je služila tudi kot orodje za hitro iskanje funkcije, ki sem jo v tistem trenutku iskala, a se nisem mogla spomniti imena.

V preostalem delu sem se opirala na znanja in način dela, ki sem se ga naučila ob praktičnih vajah na fakulteti.

## Kako bi naredila nalogo zahtevnejšo

Načinov za otežiti dano nalogo je ogromno:
- lahko bi calendar.txt igral močnejšo vlogo, torej da bi bili nekateri avtobusi "weekday" avtobusi, nekateri pa "weekend"
- dodana bi bila lahko navodila, ki bi zahtevala povezavo še ostalih gtfs datotek v aplikacijo (ali ostalih informacij iz teh gtfs datotek, npr. dodaten argument, ki zahteva, da je avtobus "wheelchair_accessible")
- v širši sliki bi lahko pregledovali urnike voženj in dejansko stanje, torej kolikšno zamudo imajo avtobusi
- lahko bi naloga zahtevala tudi izdelavo kompleksnejšega uporabniškega vmesnika, kjer bi se potencialno uporabile tudi route_color in route_text_color lastnosti)
- med stop_times.txt sem opazila, da ni hudih robnih primerov, ki bi delali preglavice s prištevanjem 2 ur k LocalTime (torej da bi ura presegla polnoč). Tudi to bi lahko bila nadgradnja - jaz sem sicer ta handling za vsak slučaj vključila v rešitev 




