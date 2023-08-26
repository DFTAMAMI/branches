package com.banquito.core.branches.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.banquito.core.branches.exception.CRUDException;
import com.banquito.core.branches.model.Branch;
import com.banquito.core.branches.repository.BranchRepository;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceTest {
    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchService branchService;

    @Test
    public void testLookByIdExistingBranch() throws CRUDException {

        Branch mockBranch = new Branch();
        mockBranch.setCode("123");
        mockBranch.setId("1");
        mockBranch.setName("prueba");
        when(branchRepository.findById("1")).thenReturn(Optional.of(mockBranch));

        Branch result = branchService.lookById("1");
        verify(branchRepository).findById("1");

        assertNotNull(result);
        assertEquals("ID no coincide", "1", result.getId());
        assertEquals(mockBranch, result);
    }

    @Test
    public void testLookByIdNonexistentBranch() {
        String id = "2";
        when(branchRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CRUDException.class, () -> branchService.lookById(id));
    }

    @Test
    public void testLookByCodeExistingBranch() throws CRUDException {

        Branch mockBranch = new Branch();
        mockBranch.setCode("123");
        mockBranch.setId("1");
        mockBranch.setName("prueba");
        when(branchRepository.findByCode("123")).thenReturn(mockBranch);

        Branch result = branchService.lookByCode("123");
        verify(branchRepository).findByCode("123");

        assertNotNull(result);
        assertEquals("ID no coincide", "123", result.getCode());
        assertEquals(mockBranch, result);
    }

}
