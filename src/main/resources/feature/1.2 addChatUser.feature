# language: ru
Функция: Добавление

	Сценарий: Открытие окна ввода нового пользователя
		Если я прошел по ссылке 'http://localhost:8080/'
                То откроется форма с таблицей 'Пользователи'
                Когда я нажму кнопку 'Добавить'
                То откроется окно с полями: 
                |ФИО:   |
                И кнопками:
                |Добавить|
                |Отмена  |

    Сценарий: Добавление нового пользователя
            Когда я введу в поле 'ФИО:' 'Александр'
            И нажму кнопку 'Добавить пользователя'
            То закроется текущее окно и отобразится страница 'Пользователи' с таблицей
            И таблица будет содержать в себе 'Александр'
                
