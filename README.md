# EX Spring La Mia Pizzeria - Spring Security

## Consegna

Ricordatevi di sganciare la vostra vecchia repository e di crearne una nuova per questo esercizio, che prosegue il lavoro della pizzeria, dove lo avevate lasciato.

Finalmente proteggiamo la nostra applicazione!

Abbiamo sviluppato tutte le pagine per gestire la nostra pizzeria (elenco pizze, dettagli singola pizza, creazione, modifica, cancellazione, offerte speciali, ingredienti)…ma vogliamo che chiunque possa effettuare queste operazioni? Sicuramente no!

Quindi inseriamo l’autenticazione in modo che solo gli utenti registrati possano accedere a queste pagine.

Creiamo le entity necessarie e popoliamo a mano i dati degli utenti nel database.

Sono previsti due ruoli : USER e ADMIN

Chi avrà assegnato come ruolo USER potrà accedere solo alla pagina index e a quella di dettaglio.

Chi invece avrà assegnato come ruolo ADMIN potrà fare tutto.
