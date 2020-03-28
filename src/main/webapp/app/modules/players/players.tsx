import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

import { IRootState } from 'app/shared/reducers';
import { getPlayers } from './players.reducer';

export interface IPlayersProp extends StateProps, DispatchProps {}

export const Players = (props: IPlayersProp) => {
  
  useEffect(() => {
    props.getPlayers();
  }, []);
  
  const playersArray = [];
  Object.keys(props.players).forEach(function(key) {
      playersArray.push(props.players[key]);
    });
  const playersList = playersArray
    .sort((a, b) => (a.name.localeCompare(b.name)));
    // .map((p) =>  <li key={p.id}>{p.name}</li>);
  
  return (
    <Row>
      <Col md="9">
        <TableContainer component={Paper}>
          <Table aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>Name</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {playersList.map((player) => (
                <TableRow key={player.key}>
                  <TableCell component="th" scope="row">
                    {player.name}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ players }: IRootState) => ({
  players: players.players,
  isFetching: players.loading
});

const mapDispatchToProps = { getPlayers };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Players);
