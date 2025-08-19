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