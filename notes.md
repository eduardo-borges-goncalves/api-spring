Anotações Sobre Java 

Eager Loading e Lazy Loading 

Regra básica: 
Tudo que termina com ToOne é eager.
Tudo que termina com ToMany é Lazy. 

Eager: carregamento ansioso, com múltiplos selects no banco. Gera o problema conhecido como n+1, 
para cada entidade, ele deve fazer n+1 selects no banco. Pode se tornar extremamente lento, pois 
quando criamos uma associação ToOne, o JPA busca todas as associações no banco com múltiplos 
Selects, mesmo quando usamos a annotation @JsonIgnore

Lazy: carregamento preguiçoso, por etapa, quando solicitado. Ele não pega os dados do banco quando 
criamos um relacionamento ManyToMany e anotamos com @JsonIgnore. Parece 100% melhor que o eager, mas 
na verdade acaba ocasionando o mesmo erro do eager quando retiramos a anotação json ignore. 
