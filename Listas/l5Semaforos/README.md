1) Um servidor com multiprocessamento recebe requisições que envolve realizar cálculos
   e fazer transações com bancos de dados. Por ter uma quantidade grande de núcleos de
   processamentos e threads, além de um bom algoritmo de escalonamento de threads,
   enquanto as threads fazem cálculos, estes podem ocorrer simultaneamente, mas
   quando se faz a transação no banco de dados, esta deve ser apenas uma thread por
   vez. Considere um conjunto de threads com IDs definidas na própria aplicação com
   números iniciando em 1 e incrementando de um em um. As threads tem comportamento
   como segue:


a) Threads com ID dividido por 3 resultando em resto igual a um fazem as transações:
- Cálculos de 0,2 a 1,0 segundos
- Transação de BD por 1 segundo
- Cálculos de 0,2 a 1,0 segundos
- Transação de BD por 1 segundo


  b) Threads com ID dividido por 3 resultando em resto igual a dois fazem as transações:
- Cálculos de 0,5 a 1,5 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 0,5 a 1,5 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 0,5 a 1,5 segundos
- Transação de BD por 1,5 segundo


  c) Threads com ID dividido por 3 resultando em resto igual a zero fazem as transações:
- Cálculos de 1 a 2 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 1 a 2 segundos
- Transação de BD por 1,5 segundo
- Cálculos de 1 a 2 segundos
- Transação de BD por 1,5 segundo


  Faça uma aplicação em Java que simule a situação de 21 Threads simultâneas, com
  exibição em console de cada passo que a Thread está realizando.


2) Existem diversos jogos de simulação. Um deles simula a participação de cozinheiros em
   uma cozinha profissional realizando pratos. Numa das fases, o cozinheiro precisa
   realizar o cozimento de 5 pratos simultâneos, onde cada cozimento não depende da
   interação do jogador. Pratos de ID ímpar, são chamados de Sopa de Cebola e levam de
   0,5 a 0,8 segundos para ficar prontos. Pratos de ID par, são chamados de Lasanha a
   Bolonhesa e levam de 0,6 a 1,2 segundos para ficar prontos. Quando um prato inicia, é
   necessário comunicar, em console, que se iniciou e, a cada 0,1 segundos, deve-se exibir
   o percentual de cozimento (O percentual é definido pelo tempo total dividido por 0,1
   segundos). Quando um prato fica pronto, é necessário comunicar em console o final e
   fazer a entrega, que leva 0,5 segundos. O jogador só pode entregar um prato por vez e
   deve comunicar a entrega. Simular a situação em Java.