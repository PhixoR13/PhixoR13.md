---
name: totalplay-wifi
description: Resuelve el error net::ERR_UNKNOWN_URL_SCHEME del portal cautivo de TotalPlay (intent://totalgo.totalplay.com.mx y Club WiFi). Guía práctica en español para usuarios de Android.
---

# TotalPlay WiFi — Soluciones prácticas (net::ERR_UNKNOWN_URL_SCHEME)

Resumen

Esta skill ofrece pasos prácticos y ordenados para resolver el error net::ERR_UNKNOWN_URL_SCHEME que aparece cuando el portal cautivo de TotalPlay intenta abrir un Intent (intent://) y falla. Está pensada para usuarios Android y no requiere root.

Contexto breve (1-2 líneas)

El portal cautivo de TotalPlay usa un enlace intent:// para intentar abrir la app oficial; si la app no está instalada, no responde o el navegador bloquea el esquema, aparece el error y no se completa la autenticación.

Soluciones (de más fácil a más avanzada)

1) Abrir la versión HTTPS del portal (la más efectiva)

- Copia la URL completa que aparece en el error (la que empieza con intent://totalgo.totalplay.com.mx/...).
- Reemplaza intent:// por https:// y elimina todo lo que esté después de `#Intent` (si existe).
- Ejemplo:
  - Original: `intent://totalgo.totalplay.com.mx/section/club_wifi?wlanuserip=...#Intent;...`
  - Convertida: `https://totalgo.totalplay.com.mx/section/club_wifi?wlanuserip=...`
- Pega la URL convertida en Chrome o Firefox y ábrela.
- Completa el login del portal cautivo.

2) Forzar apertura en navegador normal

- Mantén presionado el enlace (si la UI lo permite) y selecciona "Abrir en Chrome" o "Abrir en el navegador".
- Si no hay opción, copia la URL y pégala manualmente en una pestaña nueva de Chrome.

3) Olvidar la red WiFi y volver a conectar

- Ajustes → Wi‑Fi → Mantén presionada la red TotalPlay → Olvidar red.
- Conéctate de nuevo a la red e intenta abrir el portal (usar la solución 1 si aparece intent://).

4) Borrar datos y caché de la app TotalPlay

- Ajustes → Aplicaciones → TotalPlay (o Total Play) → Almacenamiento → Borrar datos y Borrar caché.
- Reinicia el teléfono y reconecta al Wi‑Fi.

5) Desinstalar y reinstalar la app TotalPlay

- Desinstala la app desde Ajustes o Play Store.
- Reinicia el dispositivo y conecta al Wi‑Fi. Usa la URL https:// convertida para autenticar.
- Si lo deseas, reinstala la app después de autenticar.

6) Usar otro navegador o modo invitado/incógnito

- Prueba con Firefox, Edge o el navegador del sistema.
- Abre una ventana de incógnito en Chrome y pega la URL https:// convertida.

Notas útiles

- El parámetro `wlanparameter` suele incluir la dirección MAC o identificador del equipo.
- `accname` puede identificar la cuenta o el nodo del proveedor.
- Si ninguna solución funciona, reinicia el módem TotalPlay y vuelve a intentar.
- Estas indicaciones están orientadas a usuarios finales (sin root ni ADB). Si necesitas pasos con ADB, pídemelo explícitamente.

Triggers (activación automática sugerida)

triggers:
  keywords:
    - TotalPlay
    - totalplay
    - Club WiFi
    - portal cautivo
    - ERR_UNKNOWN_URL_SCHEME
    - intent://
    - totalgo.totalplay.com.mx
    - com.TotalPlay.totalplay
    - Página web no disponible
  phrases:
    - "no puedo conectarme al WiFi de TotalPlay"
    - "me sale error de intent"
    - "página web no disponible TotalPlay"
    - "el portal de TotalPlay no carga"
    - "error net::ERR_UNKNOWN_URL_SCHEME"
  urls:
    - "intent://totalgo.totalplay.com.mx"
    - "https://totalgo.totalplay.com.mx/section/club_wifi"

---

¿Deseas que añada capturas de ejemplo y una sección con comandos ADB seguros (solo si lo pides)?
