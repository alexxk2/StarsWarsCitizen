# StarsWarsCitizen

Приложение для поиска и сохранения любимых персонажей, планет и звездолетов из вселенной StarWars.
Приложение состоит их трех экранов с bottom navigation.<br>
<br>**Экран поиска**
<br>На экране поиска можно найти нужного персонажа или объект по ключевому слову, поиск срабатывает только при вводе двух и более символов, также реализован debaunce в 2 секунды для поиска и дебаунс в 3 
секунды для проверки условия >= 2 символа в поиске.
Список найденного выводится в recyclerView. В зависимости от объекта (планета, человек, звездолет) будет отображатся соответствующий значок в списке поиска. Информация для разных
объектов поиска отображается разная. Понравившийся объект или персонажа можно добавить или удалить из избранного по кнопке, изменение статуса отобразится сразу же. При добавлении элемент попадет 
в Ваш список избранного.<br>
<br>**Экран избранного**
<br>Список избранного реализован с помощью Room. 
На главных экранах (поиск и избранное) реализована модель LCE. Если поиск не дал результатов или была какая-то ошибка - будет соответствующая заглушка.<br>
На экране избранного Вы видите элементы, которые добавили. С этого экрана также можно удалить из избранного.
При поиске все элементы проверяются на наличии в избранном и отобразятся с актуальным статусом.<br>
<br>**Экран избранного**
<br>На экране настроек можно выбрать темную или светлую сторону Силы))(темная/светлая тема приложения).<br>
Добавлен splashScreen.

## Используемый стек

+ Kotlin
+ Clean Architecture
+ Koin
+ Retrofit
+ Room
+ SOLID
+ MVVM (ViewModel, LiveData)  
+ RecyclerView & DiffUtil  
+ ViewBinding  
+ Navigation Component  
+ Glide
+ Coroutines
+ SplashScreen
  

## Gif flow приложения

<img src="https://github.com/alexxk2/StarsWarsCitizen/blob/master/app/src/main/res/drawable/flow_1.gif" width="340" height="699" />  <br>
<img src="https://github.com/alexxk2/StarsWarsCitizen/blob/master/app/src/main/res/drawable/flow_2.gif" width="340" height="699" />  <br>

