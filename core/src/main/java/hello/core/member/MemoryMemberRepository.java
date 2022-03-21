package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    //Member type member 객체를 받아서 id,member 를 store에 저장.
    public void save(Member member) {
        store.put(member.getId(), member);
    }
    //Member type member 객체의 memberId 를 받아 store에서 member 객체를 반환.
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
