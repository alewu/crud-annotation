package com.ale.optional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
  * @author alewu
  * @date 2018/3/23
  * @description optional测试
  */
public class OptionalDemo {
    @Test
    public void test(){
        Integer i = 2;
         i = Optional.ofNullable(i).orElse(0);
        System.out.println(i);
    }

    @Test
    public void test02(){
        RecordDTO recordDTO = new RecordDTO(1, 1, "ok");
        RecordDTO recordDTO1 = new RecordDTO(1, 2, "ok");
        RecordDTO recordDTO2 = new RecordDTO(1, 3, "ok");
        RecordDTO recordDTO3 = new RecordDTO(1, 4, "ok");
        RecordDTO recordDTO4 = new RecordDTO(2, 5, "ok");
        RecordDTO recordDTO5 = new RecordDTO(2, 6, "ok");
        List<RecordDTO> recordDTOS = new ArrayList<>();
        recordDTOS.add(recordDTO);
        recordDTOS.add(recordDTO1);
        recordDTOS.add(recordDTO2);
        recordDTOS.add(recordDTO3);
        recordDTOS.add(recordDTO4);
        recordDTOS.add(recordDTO5);
        List<RecordDTO> recordDTOList = recordDTOS.stream().filter(r -> r.getType() == 3).collect(Collectors.toList());
        System.out.println( recordDTOList.size());
        recordDTOList.forEach(System.out::println);

        if (recordDTOList.isEmpty()){
            System.out.println("空空");
        }
        Integer a = recordDTOS.stream().filter(r -> r.getType() == 3).mapToInt(RecordDTO::getTime).sum();
        System.out.println(a);



    }
}
