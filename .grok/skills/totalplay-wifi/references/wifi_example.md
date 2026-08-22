# Ejemplo: credencial/identificador WiFi

Este archivo contiene un ejemplo de identificador que puede aparecer en los parámetros del portal cautivo de TotalPlay. Está pensado para copiar/pegar cuando hagas conversiones de URL o identifiques tu sesión en el portal.

Identificador de ejemplo:

`001A2ALAR1416008376@prodigyweb.com.mx`

Cómo y dónde puede aparecer

- Como `accname` en el Intent o en la URL del portal (ejemplo):

  `intent://totalgo.totalplay.com.mx/section/club_wifi?accname=001A2ALAR1416008376%40prodigyweb.com.mx&wlanuserip=192.168.0.123#Intent;...`

- Como parte de la información de sesión o notificación (puede ser una cuenta o un identificador del acceso).

Recomendaciones de uso

1. Para convertir la URL intent:// a https://, reemplaza `intent://` por `https://` y elimina `#Intent` y todo lo posterior. Ejemplo:

   Original:

   `intent://totalgo.totalplay.com.mx/section/club_wifi?accname=001A2ALAR1416008376%40prodigyweb.com.mx&wlanuserip=192.168.0.123#Intent;...`

   Convertida:

   `https://totalgo.totalplay.com.mx/section/club_wifi?accname=001A2ALAR1416008376%40prodigyweb.com.mx&wlanuserip=192.168.0.123`

2. Si copias y pegas la URL convertida, asegúrate de decodificar `%40` a `@` si prefieres leerla; el navegador acepta la forma codificada.

3. No publiques estos identificadores si son credenciales privadas. Aquí se usa solo como ejemplo según tu petición.

¿Quieres que añada este identificador también al `references/skill.json` como ejemplo (`sample_identifier`) o que lo incluya directamente en el `SKILL.md` como ejemplo visible?