# Prova
La conversione è giunta al termine con un errore, la ListDetailActivity non carica tutti i titoli in effetti ne carica soltanto uno.
Ho provato in tutti i modi, tornando a Java con l'Adapter, con l'Activity, col JsnDetail, ecc..
Non sono riuscito a trovare l'errore sono due giorni che ci giro intorno ma niente, probabilmente è un errore così insignificante che non riesco a trovarlo.
Ho aggiunto il file DialogError.kt per evitare di riscrivere il codice in tutti i file dove mi serve una dialog ed ho aggiunto il file CustomProgressDialog.kt perché la ProgressDialog è deprecata.
Non mi resta che studiarmi il Kotlin con il corso "Android Apps with Kotlin: Build Your First App".

Naturalmente l'errore era una cosa talmente ovvia che non l'avevo notata,basta modificare il layout detail_layout così:
<androidx.constraintlayout.widget.ConstraintLayout

    xmlns:android="http://schemas.android.com/apk/res/android"
    
    xmlns:app="http://schemas.android.com/apk/res-auto"
    
    xmlns:tools="http://schemas.android.com/tools"
    
    android:layout_width="match_parent"
    
    android:layout_height="wrap_content"
    
    android:layout_margin="12dp">
    
Grazie Ornella

Fabio
