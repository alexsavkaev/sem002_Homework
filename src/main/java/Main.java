public class Main {
    public static void main(String[] args) {
//        Дана строка sql-запроса "select * from students where ".
//        Сформируйте часть WHERE этого запроса, используя StringBuilder и/или String.
//        Данные для фильтрации приведены ниже в виде json-строки.
//        Если значение null, то параметр не должен попадать в запрос.
//        Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
        StringBuilder whereClause = new StringBuilder("select * from students where ");

        String line = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
        String[] pairs = line.replaceAll("[{}\"]", "").split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (!keyValue[0].equals("null") && !keyValue[1].equals("null"))
                whereClause.append(keyValue[0]).append(" = '").append(keyValue[1]).append("' and ");
        }
        whereClause.delete(whereClause.length()-5, whereClause.length());
        System.out.println(whereClause);
//        Сравнить время выполнения замены символа "а" на "А" любой строки
//        содержащей >1000 символов средствами String и StringBuilder.
        long start = System.nanoTime();
        String text = "Варианты пересечения Карельского перешейка я перебирал давно." +
                " Поначалу я разрабатывал маршрут от Кузнечного к Светогорску - порядка 100 км по азимутам и грунтовкам. " +
                "Вторым, более простым путем, был азимут от Гаврилово примерно до Отрадного с переправой через Вуоксу в районе шлюза Гремучего." +
                " Все эти маршруты были целиком и полностью пешими и покупка пакрафта Полюд изменила все." +
                "Предполагавшаяся нитка маршрута: Гаврилово - озеро Гагачье - озеро Ториковское - Житково - Барышево (стапель) - Лосево (антистапель)" +
                "Реальная: Гаврилово - Озеро Гагачье - озеро Ториковское - Житково - мост через реку Булатная (Холодный ручей) " +
                "(стапель) - Колокольцево (антистапель) - мост через реку Волчья у Ягодного (стапель) - Лосево (антистапель)" +
                "Изначально я хотел пройти маршрут за 2 дня, выехав из города с утра, однако в последний момент решил стартовать вечером." +
                " Ночью, при лунном свете, я протопал хорошо знакомые 7 километров до озера Гагачьего и завалился спать " +
                "на первом попавшемся участке. К счастью, для бивуака мне нужно 1.5х0.5 м. ровного пространства - " +
                "я сплю в самодельном бивачном мешке и по необходимости ставлю на треккинговые палки тент.";
        System.out.println(text.replace('а', 'А'));
        long time1 = System.nanoTime()-start;
        start = System.nanoTime();
        StringBuilder text2 = new StringBuilder(text);
        int index = text2.indexOf("а");
        while (index != -1) {
            text2.replace(index, index + 1, "А");
            index = text2.indexOf("а", index + 1);
        }
        System.out.println(text2);
        long time2 = System.nanoTime()-start;
        System.out.println("String: " + time1 + "\n" + "StringBuilder: " + time2);
        if (time1 < time2) {
            System.out.println("String быстрее.");
        }else System.out.println("StringBuilder быстрее.");

    }
}
