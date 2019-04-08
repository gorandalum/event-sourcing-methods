### Event sourcing med mongodb

Pluss:
- Kjent og kjært
- Tilbys av alle. (PaaS, SaaS, IaaS osv.)
- Prosessering blir da plain old Java, hvis det er ønskelig
- Kan utnytte spørringer for å hente ut datasettene man vil bygge tilstand av

Minus:
- Ikke innebygde løsninger for å bygge opp tilstand
- Skalerer dårligst
- Må utvikle logikk for snapshotting selv
- Må løse nyansene selv med rekkefølge, exactly-once osv.
