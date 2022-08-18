package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){

        Item item = new Item("itemA",10000,10);

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());
        System.out.println("savedItem = " + savedItem);
        System.out.println("findItem = " + findItem);

        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();
        System.out.println("result = " + result);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1,item2);
    }

    @Test
    void update(){
        Item item1 = new Item("item1",10000,10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("item2", 20000, 20);
        itemRepository.update(itemId,updateParam);

        Item findItem = itemRepository.findById(itemId);
        System.out.println("findItem = " + findItem);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
