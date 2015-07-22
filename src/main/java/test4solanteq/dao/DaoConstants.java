package test4solanteq.dao;

/**
 * @author vladislav.trofimov@emc.com
 */
@SuppressWarnings({"InterfaceNeverImplemented", "unused"})
interface DaoConstants {
    String POSITION_ID = "position_id";

    @SuppressWarnings({"PublicInnerClass", "InnerClassFieldHidesOuterClassField", "unused"})
    interface Users {
        String USERS_TABLE = "users";
        String USER_ID = "user_id";
        String FIRST_NAME = "first_name";
        String LAST_NAME = "last_name";
        String MIDDLE_NAME = "middle_name";
        String BIRTH_DATE = "birth_date";
        String POSITION_ID = DaoConstants.POSITION_ID;
    }

    @SuppressWarnings({"PublicInnerClass", "InnerClassFieldHidesOuterClassField", "unused"})
    interface Positions {
        String POSITIONS_TABLE = "positions";
        String POSITION_ID = DaoConstants.POSITION_ID;
        String POSITION_NAME = "name";
    }
}