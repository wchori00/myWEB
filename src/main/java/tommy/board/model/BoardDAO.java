package tommy.board.model;

public class BoardDAO {

	private static BoardDAO instance = null;
	
	private BoardDAO() {
	}
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized(BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	// �̰��� �Խ��� �۾��� ��� �ϳ��ϳ��� �޼ҵ�� �߰� �� ���̴�
	
	
	
	
	
}
