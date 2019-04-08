### Event sourcing med EventStore

Pluss:
- Rimelig populært og god dokumentasjon
- Laget av Greg Young
- EventStore bygger opp og persistere tilstand selv, uten at man selv trenger et kjørende system.
- Kan håndtere millioner av strømmer. Man legger ofte opp til en strøm per entitet (f.eks.) kunde og et tilstands-endepunkt for hver av de.
- Kan lenke til eventer, mindre duplisering
- Skalerer meget bra

Minus:
- Tilbys av få. (PaaS, SaaS, IaaS osv.)
- Ikke så mange guides og eksempler som Kafka, spesielt når det kommer til kjøring av store produksjonsmiljøer.
- Web GUI erfaringsmessig litt buggy
