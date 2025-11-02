Best practices

1. Use BIGINT UNSIGNED AUTO_INCREMENT for primary key field as it gives us functionally infinite range
2. Service layer should define transaction boundary
3. All read only query should use dto projection
4. Every transaction occupies a database connection. So only do database interaction in an open transaction avoid performing 
     other task which consumes tie like calling external API in an open transaction. Use transactionTemplate.
5. Always used fetch type LAZY. For fetching associations.
6. Use JOIN FETCH to fetch related entity if required.
7. Use pagination to limit number of records fetched.



ManyToMany
User <=> TodoList

OneToMany
TodoList <=> TodoItem

OneToOne
TodoList <=> TodoDetail


User(id, userName, email, Gender, city, country)

TodoList(title, isPinned)

TodoDetail(
formattingOptions(headingOne, headingTwo, Bold, Italic, underline),
backgroundOptions(Colors(yellow, orange), category(Food, music, note, travel, music)),
RemindMeOption(Date, time, location)
)

TodoItem(rank, isCompleted, content)

C : Post
R : Get
U : Put, Patch
D : Delete