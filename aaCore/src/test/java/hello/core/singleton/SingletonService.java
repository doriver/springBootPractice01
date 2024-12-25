package hello.core.singleton;

// 싱글톤 패턴
public class SingletonService {
	// static영역에 자기자신객체를 1개만 생성해 둔다
	private static final SingletonService instance = new SingletonService();
	
	// 생성자를 private 로 선언해 외부에서 new키워드로 객체생성 못하게 막는다
	// SingletonService sgt = new SingletonService(); 이렇게 호출못함
	private SingletonService() {}
	
	// public으로 열어 객체 인스턴스가 필요하면 이 static메서드를 통해 조회하도록 한다.
	public static SingletonService getInstance() { return instance; }
	
	public void logic() {
		System.out.println("싱글톤 객체로 로직 호출");
	}
}
