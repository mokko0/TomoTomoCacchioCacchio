# TomoTomoCacchioCacchio

# Street Artist Simulation

Questo programma simula una situazione in cui un artista di strada esegue caricature e ritratti a carboncino. Ci sono quattro sedie disponibili per i clienti che vogliono farsi fare un ritratto. I clienti arrivano in modo casuale e attendono il loro turno per spostarsi nella zona di lavoro. Tuttavia, se un cliente aspetta troppo a lungo per una sedia libera (più di `MAX_WAIT_TIME` millisecondi), rinuncia a farsi fare il ritratto.

## Algoritmo

L'algoritmo utilizzato in questo programma è abbastanza semplice. Il programma crea continuamente nuovi thread `Customer` che rappresentano i clienti che arrivano per farsi fare un ritratto. Ogni thread `Customer` tenta di acquisire una sedia utilizzando il metodo `tryAcquire` del semaforo `availableChairs`. Se il metodo `tryAcquire` restituisce `false`, ciò significa che il cliente ha aspettato troppo a lungo per una sedia libera e rinuncia a farsi fare il ritratto. Altrimenti, il cliente si siede in una sedia e attende il suo turno per spostarsi nella zona di lavoro. Una volta che il ritratto è finito, il cliente rilascia la sedia utilizzando il metodo `release` del semaforo `availableChairs`.

## Sincronizzazione dei processi

Il programma utilizza i semafori come meccanismo di sincronizzazione tra i processi. Un semaforo è un oggetto che mantiene un conteggio delle risorse disponibili e fornisce metodi per acquisire e rilasciare queste risorse in modo sicuro tra più thread. In questo caso, le risorse sono le sedie disponibili per i clienti.

Il semaforo `availableChairs` viene inizializzato con il numero di sedie disponibili (`NUM_CHAIRS`). Quando un thread `Customer` tenta di acquisire una sedia utilizzando il metodo `tryAcquire`, il semaforo decrementa il conteggio delle risorse disponibili. Se il conteggio delle risorse disponibili è zero, ciò significa che non ci sono sedie libere e il metodo `tryAcquire` restituisce `false`. Quando un thread `Customer` rilascia una sedia utilizzando il metodo `release`, il semaforo incrementa il conteggio delle risorse disponibili.

## Problemi di stallo e starvation

In questo programma non ci sono problemi di stallo o starvation. 

-Un problema di stallo si verifica quando due o più thread sono bloccati in attesa l'uno dell'altro e nessuno può procedere. In questo caso, non ci sono situazioni in cui i thread possono bloccarsi in attesa l'uno dell'altro, quindi non ci sono problemi di stallo.

-Un problema di starvation si verifica quando un thread è costantemente impedito dall'acquisire una risorsa a causa della concorrenza con altri thread. In questo caso, ogni thread `Customer` ha la stessa possibilità di acquisire una sedia, quindi non ci sono problemi di starvation.
