# language: ru
Функция: Удаление


	Сценарий: Удаление пользователя
		Если я прошел по ссылке 'http://localhost:8080/'
                То откроется форма с таблицей 'Пользователи'
                Если выбрать:
                |AlexanderMv|
                И нажать кнопку 'Удалить'
                То таблица не будет содержать в себе 'Сашок'
