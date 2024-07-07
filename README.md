![detecção-poses-banner](https://github.com/git-jr/3800-Deteccao-Poses-Android-MLKit/assets/35709152/149c1e55-86a4-4519-9fee-388a8365fe94)

# MexeAI
Um aplicativo Android que incentiva a atividade física usando detecção de poses para misturar o mundo real com o virtual através de um jogo dinâmico, que usa modelos de Machine Learning local para detectar os movimentos em frente à câmera do celular. Tudo isso graças ao uso do ML Kit e às animações sofisticadas do Jetpack Compose.
Bora tentar bater esse recorde?


## 🔨 Funcionalidades do projeto
https://github.com/git-jr/3800-Deteccao-Poses-Android-MLKit/assets/35709152/b0db878a-f13f-4f17-9161-9ab08a5ad635



### ✨ Detecção de poses
- Sempre que alguém se posicionar em frente à câmera, o app tentará identificar uma pessoa começando pelo rosto.
- Cada um dos 33 pontos de referência do corpo humano é mapeado e desenhado na tela ou tem sua posição estimada quando não visível totalmente.
- O app usa heurísticas de ângulos para identificar poses específicas.
- Quando uma pose com os braços levantados é identificada, o app começa o jogo automaticamente.
- O app utiliza algoritmos de detecção de colisão para verificar a aproximação das mãos do usuário com pontos vermelhos que aparecem aleatoriamente na tela e, quando colidem, aumentam a pontuação geral.
- Os algoritmos de colisão também são usados para detectar quando as mãos do usuário se juntam após o fim do jogo para reiniciá-lo.


  
### 📱Telas
- **Início:** exibe a pontuação mais alta obtida; opção de troca da câmera a ser usada nas detecções (frontal ou traseira) e botão para iniciar.
- **Tutoriais:** guias de orientação sobre quais poses e gestos devem ser feitos para disparar as ações no app.
- **Tela principal:** exibe o feed da câmera escolhida com uma sobreposição de elementos gráficos, ícones e textos para auxiliar no jogo, como tempo restante para o fim da partida, contagem de pontos atual, ícones avisando sobre o status do jogo.
- **Tela principal (pontos):** as mãos do usuário têm duas esferas que flutuam, indicando os pontos de contato com uma esfera vermelha aleatória que deve ser atingida. Sempre que as mãos tocam na esfera vermelha, a borda da tela pisca para indicar a colisão e a esfera vermelhante sobe para reaparecer em outro ponto aleatório até o fim do jogo.


## ✔️ Técnicas e tecnologias utilizadas

As técnicas e tecnologias utilizadas pra isso são:

- `Jetpack Compose`: kit de ferramentas moderno para criar IUs em dispositivos móveis.
- `Kotlin`: linguagem de programação.
- `Gradle Version Catalogs`: nova forma de gerenciar plugins e dependências em projetos Android.
- `Material Design 3`: padrão de design recomendado pela Google para criação de UI modernas.
- `Hilt`: injeção de dependências.
- `Navigating with Compose`: navegação entre composables e telas.
- `Viewmodel, states e flow`: gerenciamento de estados e controle dos eventos disparados pelas detecções do modelo da Google.
- `DataStore`: mantém, após o fechamento do app, a última pontuação mais alta e a câmera preferida para usar no jogo.
- `ML Kit Pose Detection`: biblioteca para detecção de poses humanas em tempo real, utilizando modelos de aprendizado de máquina para analisar e interpretar movimentos do corpo capturados pela câmera.
- `CameraX`: biblioteca do Jetpack que facilita a integração de funcionalidades de câmera em aplicativos Android, abstraindo a complexidade da API de câmera do Android e oferecendo uma interface simples para captura de fotos e vídeo.
- `Camera Permissions`: gerencia o acesso à câmera do dispositivo, solicitando permissão ao usuário para utilizá-la nas detecções e interações dentro do aplicativo.
- `CameraAnalyzer`: componente utilizado junto ao CameraX para analisar frames de vídeo em tempo real, permitindo a implementação de funcionalidades de detecção que requeiram processamento frame a frame.


## 📁 Acesso ao projeto

- Versão inicial: Veja o [código fonte][codigo-inicial] ou [baixe o projeto][download-inicial]
- Versão final: Veja o [código fonte][codigo-final] ou [baixe o projeto][download-final]

## 🛠️ Abrir e rodar o projeto
Após baixar o projeto, você pode abri-lo com o Android Studio. Para isso, na tela de launcher clique em:

“Open” (ou alguma opção similar), procure o local onde o projeto está e o selecione (caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo). Por fim, clique em “OK” o Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆


## 📚 Mais informações do curso

Gostou do projeto e quer conhecer mais? Você pode [acessar a formação com esse e muitos outros cursos](https://www.alura.com.br/formacao-android-ia-google-ml-kit) relacioandos ao tema de Machine Learning e IA no Android.

[codigo-inicial]: https://github.com/alura-cursos/3800-Deteccao-Poses-Android-MLKit/commits/projeto-inicial/
[download-inicial]: https://github.com/alura-cursos/3800-Deteccao-Poses-Android-MLKit/archive/refs/heads/projeto-inicial.zip

[codigo-final]: https://github.com/alura-cursos/3800-Deteccao-Poses-Android-MLKit/commits/aula-5/
[download-final]: https://github.com/alura-cursos/3800-Deteccao-Poses-Android-MLKit/archive/refs/heads/aula-5.zip
