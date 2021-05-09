import java.util.Arrays;
import java.util.HashSet;

public class P_불량사용자 {

	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
		
		System.out.println(solution(user_id, banned_id));
	}

	private static int solution(String[] user_id, String[] banned_id) {
		int answer = 1;
		
		HashSet<String> hs = new HashSet<String>();
		int bannedSize = banned_id.length;
		int userSize = user_id.length;
		
		for (int i = 0; i < bannedSize; i++) {
			hs.add(banned_id[i]);
		}
		
		int[] bannedCnt = new int[hs.size()];
		int idx = 0;
		for(String str : hs) {
			for (int i = 0; i < bannedSize; i++) {
				if (banned_id[i].equals(str)) {
					++bannedCnt[idx];
				}
			}
			++idx;
		}
		
		int[] userCnt = new int[hs.size()];
		int[] userIdCnt = new int[userSize];
		idx = 0;
		for (String str : hs) {
			boolean flag = true;
			
			for (int i = 0; i < userSize; i++) {
				if (str.length() != user_id[i].length()) {		// 글자수 다르면 해당 없음
					continue;
				}
				
				flag = true;
				for (int j = 0, size=str.length(); j < size; j++) {
					if (str.charAt(j) == '*') continue;
					else {
						if (str.charAt(j) != user_id[i].charAt(j)) {
							flag = false;
							break;
						}
					}
				}
				
				if (flag) {
					++userCnt[idx];
					++userIdCnt[i];
				}
			}
			++idx;
		}
		
		//System.out.println("userIdCnt : " + Arrays.toString(userIdCnt));
		for (int i = 0, size=hs.size(); i < size; i++) {
			answer *= nCr(userCnt[i], bannedCnt[i]);
		}
		
		for (int i = 0; i < userSize; i++) {
			if (userIdCnt[i] != 1) {
				answer -= (Math.pow(2, userIdCnt[i]) - userIdCnt[i] - 1);
			}
		}
		
//		System.out.println("hs : " + hs);
//		System.out.println("banned : " + Arrays.toString(bannedCnt));
//		System.out.println("user : " + Arrays.toString(userCnt));
		return answer;
	}

	private static int nCr(int n, int r) {
		int result = 1;
		for (int i = 1; i < n+1; i++) {
			result *= i;
		}
		
		int val = 1;
		for (int i = 1; i < r+1; i++) {
			val *= i;
		}
		result /= val;
		
		for (int i = 1; i < n-r+1; i++) {
			result /= i;
		}
		
		return result;
	}

}
