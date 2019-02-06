# ProsjektOppgave
Prosjektoppgave fra 2017 hvor jeg samarbeidet med en annen student, Alve Elde.
Oppgaven var å lage en asp-interpreter i Java.
(Oppgaven ble skrevet for halvannet år siden så kommentarene her er kanskje ikke helt accurate.)

I starten av oppgaven fikk vi de fire hovedklassene fra foreleser som skjelettkode.
Videre fikk vi et kompendium for å forstå asp og akkurat hva oppgaven skulle gjøre.
Kompendiumet kan finnes her: https://www.uio.no/studier/emner/matnat/ifi/INF2100/h17/pensumliste/kompendium.pdf .

Første del av prosjektet var å skrive scanneren som tyder asp-teksten og gjør det om til tokens, dette gjøres i scanner-klassen.
Neste del var parseren som sjekker at alle tokensa som ble lest inn opprettholder asp-syntaksen. Dette gjøres i parser-klassen.
Etter at koden kom gjennom parseren ble variablene insansert i et runtime-environment og koden kjørt.

Oppgaven ble godkjent da alle asp-filene som ble kjørt ga riktig resultat.
