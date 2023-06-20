package fp.art.stroke.common.listener.model.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VisitCountVO{
    private int visit_id;
    private String visit_ip;
    private Date visit_time;
    private String visit_refer;
    private String visit_agent;

    //constructor/setter/getter 생략
}

