package tommy.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// ��û �Ķ���ͷ� ��ɾ �����ϴ� ����� ���� �������̽�

// �������� ��ɾ �����ϴ� ������� ����
// Action �������̽��� �ش�Ǵ� ���� �������̽��̴�

public interface CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
	// �������� ��ɾ �����ϴ� ������� ����
	// execute �޼��忡 �ش��ϴ� ���̴�

}
