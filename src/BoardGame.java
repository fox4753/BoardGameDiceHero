import java.util.Random;
import java.util.Scanner;


public class BoardGame {

    static Scanner scn = new Scanner(System.in);
    static int[][] board = { //보드판 초기화
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    static String pname = ""; //   플레이어 이름

    public static String initgame(){
        System.out.println("#######     #####     ######   #######    #     #    #######   #######      #######            ");
        System.out.println("#      #      #      #         #          #     #    #         #     #     #       #           ");
        System.out.println("#       #     #     #          #######    #######    #######   ######     #         #          ");
        System.out.println("#      #      #      #         #          #     #    #         #     #     #       #           ");
        System.out.println("#######     #####     ######   #######    #     #    #######   #      #     #######            ");
        while(true) {
            System.out.println("1. 시작하기");
            System.out.println("2. 게임하는 법");
            System.out.println("3. 종료");
            String sinput = scn.nextLine();
            if (sinput.equals("1")) {
                System.out.println(" 보드게임에 오신 것을 환영합니다! 이름을 입력해주세요.");
                String pname = scn.nextLine();
                System.out.println("환영합니다. " + pname + "님!");
                return pname;
            } else if (sinput.equals("2")) {
                System.out.println("준비중");
            } else if (sinput.equals("3")) {
                System.out.println("게임 종료");  // 소켓종료로 변경
                System.exit(0);
            } else System.out.println("1, 2, 3중에 입력해주세요!");
        }

    }
    public static void Refreshboard(int[][] board) {
        for( int i = 0; i < board.length ; i++ ){
            int[] inboard = board[i];

            for( int j = 0; j < inboard.length ; j++) {
                if (inboard[j] == 1) {
                    System.out.print("# ");
                } else if (inboard[j] == 0) {
                    System.out.print(" ");
                } else if (inboard[j] == 2) {
                    System.out.print("@ ");
                    board[i][j] = 1;
                }
            }
            System.out.println("");
        }
        System.out.println("");


    }

    public static int dice(int value){
        Random rand = new Random();
        int dice = (rand.nextInt(value) + 1);

        return dice;
    }

    public static void win(){
        System.out.println(pname +"님의 승리입니다!");
        System.exit(0);
    }
    public static int move(int ploc, int dicenum) { // 플레이어 이동
        int num = dicenum;
        int updateloc = 0;
        updateloc += ploc + dicenum;
        int col = updateloc / 11;
        int row = updateloc % 10;

        // 승리먼저 계산
        if ( updateloc >= 30){
            board[2][9] = 2;
            Refreshboard(board);
            win();
        }
        if (row == 0)
            row = 10;

        //System.out.println("세로값 " + col);
        //System.out.println("가로값 " + row);
        //System.out.println("num값 " + num );

        for(int i = 0; i <= row - 1; i++){
            if(i != num) {
                board[col][i] = 1;
            }
            if(i == row - 1){
                board[col][i] = 2;
            }
        }
        event();
        return updateloc;

    }
    public static void event() {
        // 날개   :  주사위 1 굴림 성공시 앞으로 3칸 이동
        String[] wing = {"1", "날개", "날개다! 성공하면 날아갈 수 있을 거야", "날개 : 주사위 1이 나오면 앞으로 3칸 이동합니다. "};
        // 빙판길 : 주사위 3 이상 굴림 실패시 뒤로 3칸 이동
        String[] icyroad = {"3","빙판길", "빙판길이야! 조심하지 않으면 미끄러질거야!", "빙판길 : 주사위 3이 나오면 3칸 뒤로 이동합니다. "};
        // 슬라임 : 주사위 2 이상 굴림 실패시 뒤로 2칸 이동
        String[] slime = {"2", "슬라임", "슬라임 : 보글보글", "슬라임 : 주사위 3이상 나오면 승리합니다. 패배시 2칸 뒤로 이동합니다. "};
        // 고블린 : 주사위 3 이상 굴림 실패시 뒤로 3칸 이동
        String[] goblin = {"3", "고블린", "고블린 : 키익", "고블린 : 주사위 4이상 나오면 승리합니다. 패배시 3칸 뒤로 이동합니다. "};
        // 오크   : 주사위 4 이상 굴림 실패시 뒤로 4칸 이동
        String[] orc = {"4", "오크", "오크 : 취익", "오크 : 주사위 5이상 나오면 승리합니다. 패배시 4칸 뒤로 이동합니다. "};
        // 드래곤 : 주사위 6 굴림 실패시 처음으로 이동 성공시 1칸 앞으로 이동
        String[] dragon = {"6", "드래곤", "드래곤 : 크와앙", "드래곤 : 주사위 6이 나오면 1칸 앞으로 이동합니다. 패배시 처음으로 돌아갑니다. "};
        int[][] eventboard = new int[board.length][board[0].length]; // 2차원 배열 복사를 위한 eventboard선언

        for(int i = 0; i < board.length; i++){  // for문과 System.arraycopy를 이용한 배열 복제
            System.arraycopy(board[i], 0, eventboard[i], 0, eventboard[i].length);  //  System.arraycopy( 복제 원본, 원본의 몇 번째부터 복제할 건지, 복제 대상, 대상의 몇 번째부터 복사할 건지, 원본에서 몇 개를 복사할 건지)
        }
    }

    public static int battle(String[] monster){
        int result = 0; // 승리또는 패배시 값 반환
        int minfo = Integer.parseInt(monster[0]);

        System.out.println(monster[1] + " " + monster[2]);
        System.out.println("주사위 굴리기! Enter를 눌러주세요!");

        String button = scn.nextLine();
        while (true) {
            if (button.equals("")) {
                int num = dice(6);

                System.out.println("주사위 값 : " + num);

                if (num == minfo) { // 비길시
                    System.out.println("비겼습니다 다시 굴려주세요!");
                }else if(num > minfo) {  // 이길시  
                    System.out.println("승리");
                    return result;
                }
            } else
                System.out.println("Enter만 눌러주세요!");
        }
    }

    public static void main(String[] args) {
        /*
        int[][] board = {   {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        */

        pname = initgame();

        System.out.println("게임 시작 ! 주사위를 굴리려면 Enter를 눌러주세요!");
        String button = scn.nextLine();

        int ploc = 0;
        while (true) {
            if (button.equals("")) {
                int num = dice(6);
                System.out.println("주사위 값 : " + num);
                ploc = move(ploc, num);  // 주사위 값만큼 이동
                Refreshboard(board);

                System.out.println("주사위 굴리기 Enter 누르기!");
                button = scn.nextLine();
            }else System.out.println("Enter만 눌러주세요!");
        }




        /*
        for (int i = 0; i < 100; i++) {
            System.out.println("주사위 값 : " + (dice.nextInt(6) + 1));
        }
        */

    }


}