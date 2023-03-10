package com.example.project2.controller;

import com.example.project2.data.dto.productDTO;
import com.example.project2.service.Impl.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
//@AutoConfigureMockMvc //이 어노테이션을 통해 MockMvc를 Builder 없이 주입받을 수 있음
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ProductController에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
    // ProductController의 Autowired
    @MockBean
    ProductServiceImpl productService;

    @Test
    @DisplayName("Product 데이터 가져오기 테스트")
    void getProductTest() throws Exception{

        //given Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(productService.getProduct(1L)).willReturn(
                new productDTO(2L,"pen",5000,2000));

        Long Id = 1L;

        mockMvc.perform(
                get("/product/get/" + Id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists()) //json path의 길이가 깊어지면 .을 추가
                .andExpect(jsonPath("$.name").exists()) //andexpect : 기대값이 나왔는지 확인하는 메소드
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).getProduct(1L); //해당 객체의 메소드가 실행 되었는지 체크
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception{
        //Mock 객체에서 특정 메소드가 실행되는 경우 실제 return을 줄 수 없기 때문에 아래와 같이 기정사실 생성
        given(productService.saveProduct(1L,"pen",5000,2000)).willReturn(
                new productDTO(1L,"pen",5000,2000));

        productDTO productdto = productDTO.builder().id(2L).name("pen")
                .price(5000).stock(2000).build();

        Gson gson = new Gson();
        String content = gson.toJson(productdto);

        //아래 코드로 json 형태 변경 작업 대체 가능
        String json = new ObjectMapper().writeValueAsString(productdto);

        mockMvc.perform(
                post("/product/post")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productID").exists()) //json path의 길이가 깊어지면 .을 추가
                .andExpect(jsonPath("$.productName").exists()) //andexpect : 기대값이 나왔는지 확인하는 메소드
                .andExpect(jsonPath("$.productPrice").exists())
                .andExpect(jsonPath("$.productStock").exists())
                .andDo(print());

        verify(productService).saveProduct(1L,"pen",5000,2000);
    }
}
