package utilz;

public class Constants {
    
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    
    public static class PlayerConstants {
        
        public static final int IDLE = 0;
        public static final int WALK = 1;
        public static final int RUNNING = 2;
        public static final int CROUCH = 3;
        public static final int JUMP = 4;
        public static final int GUARD = 5;
        public static final int HIT = 6;
        public static final int ATTACK_1 = 7;
        public static final int ATTACK_2 = 8;
        public static final int ATTACK_3 = 9;
        public static final int ATTACK_4 = 10;
        
        public static int GetSpriteAmount(int player_action) {
            switch(player_action) {
                case RUNNING:
                    return 10;
                case WALK:
                    return 8;
                case ATTACK_1:
                    return 5;
                case ATTACK_2:
                case ATTACK_3:
                case ATTACK_4:
                case IDLE:
                    return 4;
                case CROUCH:
                    return 3;
                case JUMP:
                case HIT:
                    return 2;
                case GUARD:
                default:
                    return 1;
            }
        }
    }
    
}
