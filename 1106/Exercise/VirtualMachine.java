package Problem;

import java.util.*;

/**
 * 교재 명령 세트:
 *  mov x y   : x <- value(y)
 *  add x y   : x <- x + value(y)
 *  sub x y   : x <- x - value(y)
 *  jn0 v a   : value(v) != 0 이면 a(0-based) 행으로 점프
 *  prt x     : value(x) 출력
 *  exi       : 실행 즉시 종료(메모리 덤프)
 *  go        : 프로그램 입력 종료 후 실행 시작 (입력 단계에서만 사용)
 */
public class VirtualMachine {

    // ==== 상태 ====
    private final Vector<String> program = new Vector<>();         // 코드 저장
    private final HashMap<String, Integer> memory = new HashMap<>();// 변수 테이블
    private int pc = 0;                                            // 프로그램 카운터(0-based)

    // ==== 유틸 ====
    private static int valueOf(String token, Map<String,Integer> mem) {
        try { return Integer.parseInt(token); }
        catch (NumberFormatException e) { return mem.getOrDefault(token, 0); }
    }

    private static void dump(Map<String,Integer> mem) {
        if (mem.isEmpty()) {
            System.out.println("(메모리 비어있음)");
            return;
        }
        for (Map.Entry<String,Integer> e : mem.entrySet())
            System.out.println(e.getKey() + ":" + e.getValue());
    }

    // ==== 입력 단계 ====
    public void loadProgram(Scanner sc) {
        System.out.println("가상 컴퓨터: 한 줄씩 명령을 입력하세요. go 입력 시 실행.");
        int lineNumber = 0;
        while (true) {
            System.out.print(lineNumber + ">> "); // 사용자가 원하는 라인 넘버 표시
            String line = sc.nextLine().trim();
            if (line.isEmpty() || line.startsWith("#")) continue;   // 공백/주석 무시
            if (line.equalsIgnoreCase("go")) break;                 // 입력 종료
            program.add(line);
            lineNumber++;
        }
    }

    // ==== 실행 단계 ====
    public void execute() {
        pc = 0;
        while (pc >= 0 && pc < program.size()) {
            String raw = program.get(pc);
            StringTokenizer st = new StringTokenizer(raw);
            String op = st.nextToken();

            switch (op) {
                case "mov": { // mov x y
                    String x = st.nextToken();
                    String y = st.nextToken();
                    memory.put(x, valueOf(y, memory));
                    pc++;
                    break;
                }
                case "add": { // add x y
                    String x = st.nextToken();
                    String y = st.nextToken();
                    memory.put(x, memory.getOrDefault(x, 0) + valueOf(y, memory));
                    pc++;
                    break;
                }
                case "sub": { // sub x y
                    String x = st.nextToken();
                    String y = st.nextToken();
                    memory.put(x, memory.getOrDefault(x, 0) - valueOf(y, memory));
                    pc++;
                    break;
                }
                case "jn0": { // jn0 v addr
                    String v = st.nextToken();
                    int addr = Integer.parseInt(st.nextToken()); // 0-based
                    if (valueOf(v, memory) != 0) pc = addr; else pc++;
                    break;
                }
                case "prt": { // prt x
                    String x = st.nextToken();
                    System.out.println("[prt " + x + "] " + valueOf(x, memory));
                    pc++;
                    break;
                }
                case "exi": { // exi
                    System.out.println("프로그램 실행 종료. 변수 상태:");
                    dump(memory);
                    return;
                }
                default: {
                    System.out.println("알 수 없는 명령(" + op + ") — 스킵");
                    pc++;
                }
            }
        }
        // exi 없이 끝까지 도달한 경우
        System.out.println("프로그램 종료(끝까지 실행). 변수 상태:");
        dump(memory);
    }

    // ==== 진입점 ====
    public static void main(String[] args) {
        VirtualMachine vm = new VirtualMachine();
        try (Scanner scanner = new Scanner(System.in)) {
            vm.loadProgram(scanner);  // 입력(0행부터 차곡차곡 저장)
            vm.execute();             // 실행
        }
    }
}
