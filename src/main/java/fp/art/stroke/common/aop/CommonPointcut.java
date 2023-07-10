package fp.art.stroke.common.aop;
import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {

	// 회원 서비스용 Pointcut
	@Pointcut("execution(* fp.art.stroke.member..*Impl.*(..))")
	public void memberPointcut() {} 
		
	// 모든 ServiceImpl 클래스용 Pointcut
	@Pointcut("execution(* fp.art.stroke..*Impl.*(..))")
	public void implPointcut() {}
	 {}
	
}
