### Основной функционал проекта Island Simulation

1. **Животные и их характеристики**  
   - Каждый вид животных (травоядные и хищники) имеет уникальные свойства:
     - **Скорость передвижения** (`maxSpeed`).
     - **Вес** (`weight`).
     - **Максимальное количество в клетке** (`maxAmount`).
     - **Объём потребляемой пищи** (`maxFood`).
     - **Голод** (`starvation`), снижающийся со временем и влияющий на выживаемость.

2. **Питание и взаимодействие**   
Животные на карте ищут пищу, используя метод `eat()`. Основные этапы:  
- Животное проверяет все объекты в текущей клетке (`getAllEntitiesInCell()`), чтобы найти подходящую цель.  
- Вероятность успешной охоты определяется с помощью настроек из YAML-файла (`Settings.probabilities`).  
- Если цель найдена:  
  - Для травоядных пищей является трава, её количество уменьшается на определённое значение.  
  - Для хищников пищей является другое животное, которое уничтожается методом `die()`.  

Успешное питание повышает сытость животного (`starvation`), увеличивая его шансы на выживание.

3. **Передвижение**  

Метод `move()` определяет, куда переместится животное на текущем шаге:  
   1) Генерация нового направления: 
   - Выбирается случайное направление (вверх, вниз, влево, вправо) с помощью `chooseDirection()`.  
   - Рассчитывается смещение в пределах максимальной скорости животного (`maxSpeed`).  

   2) Проверка границ карты: 
   - Метод `isMovementCorrect()` проверяет, чтобы новое положение не выходило за пределы карты.  

   3) Перемещение:
   - Если клетка доступна (место для новых животных есть), животное удаляется из старой клетки и добавляется в новую.  
   - Обновляется координата животного.  

4. **Размножение**  
   - Если в клетке находятся как минимум два представителя одного вида и есть место, животное может воспроизводиться.
   - Используется метод `reproduce()` и фабрика для клонирования животных.

5. **Смерть животных**  
   - Если показатель голода достигает нуля, животное умирает, и оно удаляется из карты (`die()`).

6. **Карта мира**  
   - Карта (`IslandMap`) состоит из клеток (`Cell`), в каждой из которых хранятся объекты:
     - Растения.
     - Животные.
   - Каждая клетка проверяет вместимость (`isCapacityAvailable`) и поддерживает потокобезопасность с помощью `CopyOnWriteArrayList`.

7. **Настройка и конфигурация**  
   - Все параметры животных (вероятности, характеристики) загружаются из YAML-файлов через `YamlConfigLoader`.
   - Динамическая настройка характеристик позволяет гибко изменять симуляцию.
   - YAML файлы расположены в пакете resources

