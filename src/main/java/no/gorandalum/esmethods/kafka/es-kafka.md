### Event sourcing med kafka

Pluss:
- Meget populært, mange guides og informasjon
- Tilbys av mange. (PaaS, SaaS, IaaS osv.)
- Gode biblioteker for prosessering, spesielt Kafka Streams for Java
- Gir god hjelp til å bygge opp og persistere tilstand
- Skalerer meget bra

Minus:
- Ikke i utgangspunktet laget for event-sourcing.
- Oppbygging av tilstand krever et kjørende system
- Legger opp til kun en type key per topic. Det gjør at det blir vanskelig å bruke innebygd funksjonalitet for å bygge opp forskjellige entiteter som krever forskjellige events.
- Kan kreve duplisering
- Ikke lagt opp til millioner av topics, f.eks. en per kunde
