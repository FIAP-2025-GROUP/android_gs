# Projeto Journey 🚀

Journey é um aplicativo Android nativo desenvolvido em Kotlin com Jetpack Compose. O objetivo do aplicativo é atuar como um assistente de carreira, guiando os usuários através de conversas interativas para desenvolver novas habilidades (Hard e Soft Skills), aprender sobre o uso de IA e encontrar novas oportunidades de carreira.

## ✨ Funcionalidades

*   **Autenticação de Usuário:** Sistema completo com tela de login, registro de novos usuários e lógica de autenticação.
*   **Edição de Perfil:** O usuário autenticado pode visualizar e editar seus dados cadastrais.
*   **Interface Conversacional:** Telas de chat interativas onde o usuário pode "conversar" com a IA sobre diferentes tópicos de carreira.
*   **Navegação Robusta:** Utiliza o Jetpack Navigation para Compose, gerenciando o fluxo entre as diferentes telas do aplicativo.
*   **UI Moderna:** Interface totalmente construída com Jetpack Compose e Material Design 3, incluindo um layout em tela cheia sem a `ActionBar` tradicional.

## 🛠️ Tecnologias e Bibliotecas Utilizadas

Este projeto utiliza um stack de tecnologias moderno recomendado pelo Google para o desenvolvimento Android.

*   **Linguagem:** [Kotlin](https://kotlinlang.org/)
*   **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - O toolkit moderno para construção de UIs nativas no Android.
    *   **Material Design 3:** Para componentes visuais (`Card`, `Button`, `OutlinedTextField`).
    *   **Compose Navigation:** Para gerenciar a navegação entre as telas (Composables).
*   **Arquitetura:** MVVM (Model-View-ViewModel).
*   **Persistência de Dados:** [Room](https://developer.android.com/training/data-storage/room) - Biblioteca de persistência para armazenamento local de dados do usuário (usando `Dao`, `Database` e `Entity`).
*   **Assincronismo:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) e StateFlow para gerenciar operações em background e estados da UI.
*   **Injeção de Dependência (Processamento de Anotações):** KSP (`com.google.devtools.ksp`) para o processamento do código do Room.
*   **Build System:** [Gradle](https://gradle.org/) com Kotlin DSL (`.kts`).

## ⚙️ Configuração e Como Rodar o Projeto

Para clonar e executar este projeto em sua máquina local, siga os passos abaixo.

### Pré-requisitos

*   **Android Studio:** Versão Iguana (2023.2.1) ou mais recente.
*   **JDK:** Versão 17 ou superior (geralmente já vem embarcada com o Android Studio).
*   **Emulador ou Dispositivo Físico:** Com Android API nível 26 (Oreo) ou superior.

### Passos para Execução

1.  **Clonar o Repositório:**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    ```

2.  **Abrir no Android Studio:**
    *   Abra o Android Studio.
    *   Selecione **"Open an Existing Project"**.
    *   Navegue até o diretório onde você clonou o projeto e selecione-o.

3.  **Sincronização do Gradle:**
    *   O Android Studio irá detectar os arquivos Gradle e iniciará uma sincronização (`Sync`) automaticamente. Aguarde até que o processo seja concluído. Isso pode levar alguns minutos, pois o Gradle irá baixar todas as dependências do projeto.

4.  **Executar o Aplicativo:**
    *   Verifique se a configuração de execução **`app`** está selecionada na barra de ferramentas superior.
    *   Selecione um emulador disponível ou conecte um dispositivo físico.
    *   Clique no botão **"Run" (▶)** ou use o atalho `Shift + F10`.

O aplicativo será compilado e instalado no dispositivo selecionado.

## 🔧 Detalhes da Configuração de Build

Para que o projeto funcionasse corretamente, diversas modificações foram feitas nos arquivos de build do Gradle.

1.  **`build.gradle.kts (Project: journey)`:**
    *   Foi adicionado o plugin `com.google.devtools.ksp` para dar suporte ao processamento de anotações do Room, essencial para a compilação do banco de dados.

2.  **`build.gradle.kts (Module: app)`:**
    *   **Dependência do Material 3 (Compose):** A linha `implementation(libs.androidx.material3)` foi adicionada para incluir os componentes do Jetpack Compose Material 3.
    *   **Dependência do Material Components (Views):** A linha `implementation("com.google.android.material:material:1.12.0")` foi crucial para resolver o erro `resource style/Theme.Material3.DayNight.NoActionBar not found`. Essa dependência fornece os **temas em XML** necessários para que o `AndroidManifest.xml` possa configurar uma aplicação sem a `ActionBar` padrão do sistema de Views, permitindo uma UI em tela cheia com Compose.

3.  **`libs.versions.toml`:**
    *   Este arquivo centraliza as versões e os nomes das dependências, facilitando a manutenção e atualização. As entradas para `androidx-material3`, `room`, `lifecycle-viewmodel-compose`, entre outras, são gerenciadas aqui.

4.  **`app/src/main/res/values/themes.xml` e `AndroidManifest.xml`:**
    *   O arquivo `themes.xml` foi ajustado para que o tema principal do app, `Theme.Journey`, herdasse de `Theme.Material3.DayNight.NoActionBar`.
    *   O `AndroidManifest.xml` foi então configurado para usar `@style/Theme.Journey`, aplicando o tema de tela cheia a todo o aplicativo.

---
Este README deve servir como um guia completo para qualquer pessoa que queira entender, executar e dar manutenção ao projeto Journey.
