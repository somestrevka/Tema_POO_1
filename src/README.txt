Am creat pentru fiecare tip de actiune, si anume Query, Command si 
Recomandation. Pentru fiecare actiune specifica am creat cate o clasa
 separata. 
  In pachetul commands, gasim clasele Favorite, Rating si View.
  In Favorite, cautam utilizatorul trimis de comanda, apoi verificam daca 
videoclipul dat a fost vazut si daca nu se afla deja in lista de favorite
a utilizatorului.
  In View, daca utilizatorul a mai vazut videoul respectiv, incrementam numarul
de vizionari. Daca este prima data, adaugam videoclipul la istoricul utilizato-
rului.
  In Rating, verificam daca utilizatorul a vazut deja sau nu videol respectiv. 
Dupa caz, ii lasa sau nu o recenzie. 
  
  Pachetul query contine clasele implementate pentru rezolvarea query-urilor.
  In Query, stabilim tipul de actiune pe care trebuie sa o luam.
  In Shows, in functie de criteriul primit, vom afisa primele n seriale, sortate
dupa: durata lor, numarul de aparitii in listele de favorite, cele mai vizionate
si rating. Pentru rating a fost creata o clasa diferita, clasa RatingShow.
  In MovieClass se trateaza comenzile, in functie de criteriul primit, in acelasi
mod ca in cazul clasei Shows.
  In ActorsClass, tratam query-urile pentru actori si afisam, dupa caz, primii n
in functie de premii sau de filtrele de descriere primite prin comanda.
  
 In pachetul recomandations, clasa Recomandation stabileste ce functie este apelata
in continuare. Pentru recomandare standard, se afiseaza primul video nevizionat
de catre utilizator. In clasa Search, se verifica daca cautarea poate fi efectuata.
In clasa Popular, se verifica daca exista videoclipuri care sa respecte criteriile
cerute, in caz contrar se afiseaza mesajul corespunzator. Clasa FavRec va afisa 
primele videoclipuri, sortate dupa numarul de aparitii in listele de favorite ale
utilizatorilor.

  
